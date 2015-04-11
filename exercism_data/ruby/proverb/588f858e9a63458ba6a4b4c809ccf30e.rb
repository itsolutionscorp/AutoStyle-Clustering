class Proverb
  def initialize(*args, qualifier: '')
    @words = [*args]
    @qualifier = qualifier.empty? ? @words[0] : "#{qualifier} #{@words[0]}"
  end

  def to_s
    @words.each_index.with_object(String.new) do |i, result|
      if i.eql?(@words.size-1)
        result << "And all for the want of a #{qualifier}."
      else
        result << "For want of a #{@words[i]} the #{@words[i+1]} was lost.\n"
      end
    end
  end
end
