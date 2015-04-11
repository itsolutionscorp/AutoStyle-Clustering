class Anagram

  def initialize(word)
    @word = word
  end

  def match(list)
    posibilities.select { |posibility| posibility != @word && list.include?(posibility) }
  end

  def posibilities
    @word.downcase.split("").permutation.map(&:join).uniq.reverse
  end
end
