# todo??: monkey-patches or so
#
class Bob
  def hey msg
    case msg
    when it { nil? || empty? } then 'Fine. Be that way.'
    when it { upcase == self } then 'Woah, chill out!'
    when it {  end_with? '?' } then 'Sure.'
    else 'Whatever.'
    end
  end
  include It
end


BEGIN{ # in other file
  module It
    def it &block
      Block[block]
    end

    class Block < Struct.new :block
      def === obj
        obj.instance_eval &block
      end
    end
  end
}
