class Proverb
  def initialize(*words)
    @words = words
  end

  def to_s
    arr = @words.each_index.collect do |idx|
      if idx < @words.length - 1 then
        "For want of a #{@words[idx]} the #{@words[idx+1]} was lost."
      else
        "And all for the want of a #{@words[0]}."
      end
    end
    arr.join("\n")
  end
end
