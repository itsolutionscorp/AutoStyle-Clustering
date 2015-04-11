class Crypto
  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    @plaintext ||= @message.downcase.gsub( /[^a-z0-9]/, '' )
  end

  def size
    @size ||= (Math.sqrt normalize_plaintext.size).ceil
  end

  def plaintext_segments
    @sements ||= normalize_plaintext.split('').each_slice(size).map{|slice| slice.join}
  end

  def ciphertext
    @ciphertext ||= "".tap do |ciphertext|
      for i in 0...size
        plaintext_segments.each do |segment|
          ciphertext << segment[i] unless segment[i].nil?
        end
      end
    end
  end

  def normalize_ciphertext
    @nct ||= ciphertext.split('').each_slice([size-1, 2].max).map{|slice| slice.join}.join(' ')
  end
end
