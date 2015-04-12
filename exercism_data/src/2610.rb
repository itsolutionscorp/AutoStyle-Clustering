def compute(word_one, word_two)
    if word_one.size == word_two.size
      sum = 0
      position = 0
      word_one.each_char do |a_letter|
        sum += 1 if a_letter != word_two[position]
        position += 1
      end
      sum
    else
      puts "Error string needs to be the same length"
    end
  end