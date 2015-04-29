class Crypto
  def initialize(plaintext)
    @plain_text = plaintext
  end

  def normalize_plaintext
    @normalized_plaintext ||= @plain_text.gsub(/[^a-zA-Z0-9]+/, '').downcase
  end

  def size
    @size ||= Math.sqrt(normalize_plaintext.size).ceil
  end

  def plaintext_segments
    @plaintext_segments ||= make_chunks(normalize_plaintext, size)
  end

  def normalize_ciphertext
    make_chunks(ciphertext, 5).join(' ')
  end

  private
  def make_chunks(text, chunk_size)
    length = text.length
    result = []
    start_index = 0
    while start_index < length
      result << text[start_index, chunk_size]
      start_index += chunk_size
    end
    result
  end

  def ciphertext
    unless @ciphertext
      character_matrix = plaintext_segments.map{|substring| substring.chars}
      @ciphertext = ''
      loop do
        ciphertextchunk = character_matrix.map{|plaintext_chunk| plaintext_chunk.shift || ""}.join
        break if ciphertextchunk == ''
        @ciphertext += ciphertextchunk
      end
    end
    @ciphertext
  end
end
