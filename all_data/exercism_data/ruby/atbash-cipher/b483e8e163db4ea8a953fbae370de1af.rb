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

  def self.encode(in_string)
    chars_from(in_string).reduce_with_index("") do |out_string, char, i|
      space = get_space(i)
      out_string << space << encode_char(char)
    end
  end

  private

  def self.pass_through
    Hash.new { |hash, key| key }
  end

  CIPHER = LETTERS.chars.reduce_with_index(pass_through) do |cipher, c, i|
    cipher[c] = LETTERS[LETTERS_LENGTH - i - 1]
    cipher
  end

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
