# Encodes a message by swapping z for a, y for b, etc.
# Numbers are left unchanged
# Punctionation and spaces are ignored when encoding
# A space is added to the output string every 5 characters.
class Atbash

  LETTERS = 26
  PIVOT = 2 * 'a'.ord + LETTERS - 1

  def initialize(message)
    @message = message
  end

  def self.encode(message)
    new(message).encode
  end

  def encode
    char_lists.map{ |chars| chars.join('') }.join(' ')
  end

  private

    def char_lists
      new_chars.each_slice(5).to_a
    end

    def new_chars
      clean.chars.map { |char| new_char(char) }
    end

    def clean
      @message.downcase.gsub(/[^a-zA-Z0-9]/,'')
    end

    def new_char(char)
      char =~ /\d/ ? char : swap_letter(char)
    end

    def swap_letter(letter)
      (PIVOT - letter.ord).chr
    end
end
