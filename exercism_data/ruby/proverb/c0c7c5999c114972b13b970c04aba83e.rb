class Proverb
  def initialize(*list)
    @list = list

    if list.last.is_a? Hash
      @qualifier = list.pop[:qualifier] + ' ' + list.first
    end
  end

  def to_s
    [].tap do |text|
      @list.each_with_index do |n,i|
        unless i == @list.length-1
          text << "For want of a #{n} the #{@list[i+1]} was lost."
        end
      end

      qualifier = @qualifier || @list.first

      text << "And all for the want of a #{qualifier}."
    end.join("\n")
  end
end
