#
# Autogenerated by Thrift Compiler (0.9.3)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#

require 'thrift'
require 'calculator_types'

module TCalculatorService
  class Client
    include ::Thrift::Client

    def calculate(num1, num2, op)
      send_calculate(num1, num2, op)
      return recv_calculate()
    end

    def send_calculate(num1, num2, op)
      send_message('calculate', Calculate_args, :num1 => num1, :num2 => num2, :op => op)
    end

    def recv_calculate()
      result = receive_message(Calculate_result)
      return result.success unless result.success.nil?
      raise result.divisionByZero unless result.divisionByZero.nil?
      raise ::Thrift::ApplicationException.new(::Thrift::ApplicationException::MISSING_RESULT, 'calculate failed: unknown result')
    end

  end

  class Processor
    include ::Thrift::Processor

    def process_calculate(seqid, iprot, oprot)
      args = read_args(iprot, Calculate_args)
      result = Calculate_result.new()
      begin
        result.success = @handler.calculate(args.num1, args.num2, args.op)
      rescue ::TDivisionByZeroException => divisionByZero
        result.divisionByZero = divisionByZero
      end
      write_result(result, oprot, 'calculate', seqid)
    end

  end

  # HELPER FUNCTIONS AND STRUCTURES

  class Calculate_args
    include ::Thrift::Struct, ::Thrift::Struct_Union
    NUM1 = 1
    NUM2 = 2
    OP = 3

    FIELDS = {
      NUM1 => {:type => ::Thrift::Types::I32, :name => 'num1'},
      NUM2 => {:type => ::Thrift::Types::I32, :name => 'num2'},
      OP => {:type => ::Thrift::Types::I32, :name => 'op', :enum_class => ::TOperation}
    }

    def struct_fields; FIELDS; end

    def validate
      unless @op.nil? || ::TOperation::VALID_VALUES.include?(@op)
        raise ::Thrift::ProtocolException.new(::Thrift::ProtocolException::UNKNOWN, 'Invalid value of field op!')
      end
    end

    ::Thrift::Struct.generate_accessors self
  end

  class Calculate_result
    include ::Thrift::Struct, ::Thrift::Struct_Union
    SUCCESS = 0
    DIVISIONBYZERO = 1

    FIELDS = {
      SUCCESS => {:type => ::Thrift::Types::I32, :name => 'success'},
      DIVISIONBYZERO => {:type => ::Thrift::Types::STRUCT, :name => 'divisionByZero', :class => ::TDivisionByZeroException}
    }

    def struct_fields; FIELDS; end

    def validate
    end

    ::Thrift::Struct.generate_accessors self
  end

end

