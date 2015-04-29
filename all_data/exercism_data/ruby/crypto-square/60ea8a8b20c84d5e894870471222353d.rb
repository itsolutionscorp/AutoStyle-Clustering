class Crypto
  attr_reader :normalize_plaintext, :size, :plaintext_segments, :normalize_ciphertext, :ciphertext

  def initialize(raw)
    segment = ->(text, length) { text.scan /.{1,#{length}}/ }

    @raw = @normalize_plaintext = plain = raw.gsub(/\W/, '').downcase
    @size = Math.sqrt(plain.length).ceil
    @plaintext_segments = segment[plain, size]
    @ciphertext = :zip.to_proc[* plaintext_segments.map(&:chars)].flatten.join
    @normalize_ciphertext = segment[ciphertext, plaintext_segments.length].join(' ')
  end
end
