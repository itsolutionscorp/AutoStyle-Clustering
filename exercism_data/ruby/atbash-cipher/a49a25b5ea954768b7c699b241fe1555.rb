module Enumerable
  def reduce_with_index(initial, &block)
    accumulator = initial
    each_with_index do |item, index|
      accumulator = yield accumulator, item, index
    end
    accumulator
  end
end

module Atbash
  LETTERS = "abcdefghijklmnopqrstuvwxyz"
  LETTERS_LENGTH = LETTERS.length
  SPACE_INTERVAL = 5

  CIPHER = LETTERS.chars.reduce_with_index(Hash.new { |hash, key| key }) do |cipher, c, i|
    cipher[c] = LETTERS[LETTERS_LENGTH - i - 1]
    cipher
  end

  def self.encode(in_string)
    chars_from(in_string).reduce_with_index("") do |out_string, character, index|
      space = get_space(index)
      out_string << space << encode_char(character)
    end
  end

  private

  def self.encode_char(character)
    CIPHER[character]
  end

  def self.get_space(index)
    if index > 0 && index % SPACE_INTERVAL == 0
      " "
    else
      ""
    end
  end

  def self.chars_from(in_string)
    in_string.downcase.chars.select do |c|
      c.match /\w/
    end
  end

end
