class Atbash

  class << self

    def encode(message)
      self.new(message).encode_message
    end

    def chars_to_indices
      @chars_to_indices ||= begin
        i = 0
        @chars_to_indices = ('a'..'z').map.with_object({}) { |char, mapping|
          mapping[char] = i
          i += 1
        }
      end
    end

    def indices_to_chars
      @indices_to_chars ||= self.chars_to_indices.invert
    end

    def alphabet_length
      @alphabet_length ||= chars_to_indices.length
    end

  end

  def initialize(message)
    @message = normalize(message)
  end

  def encode_message
    encoded_message = @message.chars.each.with_object('') { |char, message|
      message << encode_character(char)
    }
    group(encoded_message)
  end

  def group(message, by = 5)
    message.gsub(/(.{#{by}})/, '\1 ').strip
  end

  def encode_character(char)
    return char unless alpha?(char)
    index = self.class.chars_to_indices[char]
    encoded_index = self.class.alphabet_length - index - 1
    self.class.indices_to_chars[encoded_index]
  end

  def normalize(message)
    message.gsub(/[^[:alnum:]]/, '').downcase
  end

  def alpha?(char)
    char.match(/^[a-z]$/)
  end

end
