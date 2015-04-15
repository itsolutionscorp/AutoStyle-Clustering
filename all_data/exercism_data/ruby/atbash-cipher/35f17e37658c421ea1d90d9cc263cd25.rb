class Atbash

  def self.encode(text)
    new(text).encode
  end

  def initialize(text)
    @text = text
  end

  def encode
    each_five(reverse_letters)
  end

  private

    def each_five(letters)
      letters.scan(/.{1,5}/).join(" ")
    end

    def reverse_letters
      tranform_text.map { |letter| reverse_letter(letter)  }.join  
    end

    def alphabet
      "abcdefghijklmnopqrstuvwxyz"
    end

    def reverse_letter(letter)
      if is_integer?(letter)
        letter
      else
        index = alphabet.index(letter)
        alphabet.reverse[index]
      end
    end

    def is_integer?(letter)
      letter == letter.to_i.to_s
    end

    def tranform_text
      @text.gsub(/[\s\.\,]/, "").downcase.chars
    end

end
