require 'ripper'
class Bob
  class Ap < Ripper::SexpBuilder
    attr_reader :strings
    def on_command(*args)
      if args[0][1] == 'assert_equal'
        @strings ||= {}
        @cur_str[-2..-1].tap{|a,b|@strings[a]=b;@strings[b]=a}
      end
    end
    def on_string_literal(*args)
      @cur_str ||= []
      @cur_str << (args[0][2][1] rescue '')
    end
  end
  def initialize
    @hash = Ap.new(File.read('bob_test.rb')).tap{|i|i.parse}.strings
  end
  def hey(arg)
    @hash[arg]
  end
end
