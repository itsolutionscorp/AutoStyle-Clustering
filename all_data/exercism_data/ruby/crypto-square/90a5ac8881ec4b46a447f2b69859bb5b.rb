class Crypto
  attr_reader :plaintext
  def initialize plaintext
    @plaintext = plaintext
  end
  def normalize_plaintext
    @normalized_plaintext ||= clean plaintext
  end
  def size
    @size ||= Math.sqrt(normalize_plaintext.length).ceil
  end
  def plaintext_segments
    @plaintext_segments ||= chunk normalize_plaintext, size
  end
  def ciphertext
    @ciphertext ||= clean transposed_plaintext_segments.join
  end
  def normalize_ciphertext
    @normalized_ciphertext ||= chunk(ciphertext, 5).join(' ')
  end
  
  private
  def transposed_plaintext_segments
    plaintext_segments.map do |segment| 
      segment.ljust size
    end.map(&:chars).transpose.map(&:join)
  end
  
  def clean s
    s.downcase.gsub /\W/, ''
  end
  
  def chunk s, size
    s.scan /.{1,#{size}}/
  end
end
