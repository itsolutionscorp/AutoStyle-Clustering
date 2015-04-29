class Crypto

  attr_reader :normalize_plaintext
  attr_reader :size

  def initialize raw_word
    @normalize_plaintext = raw_word.downcase.gsub(/[^a-z0-9]/i, '')
    @size = Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    (0..size - 1).each_with_object([]) {|line, result| result << normalize_plaintext[line * size.. line * size + size - 1] if normalize_plaintext[line * size]}
  end

  def ciphertext
    (0..size - 1).each_with_object('') {|line, result| 
      plaintext_segments.each {|segment|
        result << segment[line] if segment[line]
      }
    }
  end

  def normalize_ciphertext
    ciphertext.length > 3 ? ciphertext.gsub(/(.{#{size - 1}})/, '\1 ').strip : ciphertext.gsub(/(.{#{2}})/, '\1 ').strip
  end

end
