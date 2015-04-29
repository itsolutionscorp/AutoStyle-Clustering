class Crypto
  attr_reader :normalize_plaintext
  attr_reader :size
  attr_reader :plaintext_segments
  attr_reader :ciphertext
  attr_reader :normalize_ciphertext

  def initialize(message)
    @normalize_plaintext = message.gsub(/[^a-zA-Z0-9]/,'').downcase

    @size = Math.sqrt(@normalize_plaintext.length).ceil

    @plaintext_segments = @normalize_plaintext.scan(/.{1,#{@size}}/)

    @ciphertext = (0...@size).inject("") do |cipher, i|
      @plaintext_segments.each do |segment|
        cipher += segment[i].to_s
      end
      cipher
    end
    
    @normalize_ciphertext = @ciphertext.scan(/.{1,#{@size}}/).join(' ')
  end
end
