class Proverb

  def initialize(*args)
    @args = args.grep(String)
    @quantifier = translate_to_string(args.grep(Hash))
  end

  def to_s
   @words = translate_to_hash
   insert_lines
   array_of_lines.join
 end

  def insert_lines
    @words.count.times do |count|
      unless count == 0
        @words[count] = repeating_line(@args[count - 1], @args[count])
      end
    end
    @words[0] = final_line(@words[0])
  end

  def array_of_lines
    proverb = []
    last_line = @words.delete(0)
    @words.each do |k, v|
      proverb << v
    end
    proverb << last_line
  end

  def translate_to_hash
    hash = {}
    @args.count.times do |count|
      hash[count] = @args[count]
    end
    hash
  end

  def translate_to_string(quantifier_array)
    quantifier_array.map{|quantifier| quantifier.values}.join
  end

  def repeating_line(word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def final_line(first_word)
    final_word = (@quantifier + " " + first_word).strip
    "And all for the want of a #{final_word}."
  end
end
