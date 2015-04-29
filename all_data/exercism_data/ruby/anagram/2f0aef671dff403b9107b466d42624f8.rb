class Anagram < Struct.new(:word)
  def match(candidates)
    candidates.select do |candidate|
      anagram?(word.downcase, candidate.downcase)
    end
  end

  private
  def anagram?(a, b)
    return false if a == b

    a_counts = a.each_char.with_object(Hash.new(0)) do |char, counts|
      counts[char] += 1
    end

    b_counts = b.each_char.with_object(Hash.new(0)) do |char, counts|
      return false if counts[char] == a_counts[char] # short circuit impossible match
      counts[char] += 1
    end

    a_counts == b_counts
  end
end
