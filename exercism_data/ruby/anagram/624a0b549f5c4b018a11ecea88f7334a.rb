class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    posibilities.select { |posibility| posibility != @word && list.include?(posibility) }
  end

private

  def posibilities
    @word.split("").combination.map(&:join).reverse
  end
end
