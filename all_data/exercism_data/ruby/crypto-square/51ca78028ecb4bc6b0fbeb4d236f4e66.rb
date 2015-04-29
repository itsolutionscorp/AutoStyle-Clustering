class Crypto

  def initialize plaintext
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.gsub(/\W/,'').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan /.{#{size}}|.+/
  end

  def ciphertext
    plaintext_segments.each_with_object(Array.new(size,'')) do |seg,ary|
        seg.each_char.with_index do |chr,i|
          ary[i] += chr # why doesn't the shovel (<<) operator work?
      end
    end.join
  end

  def normalize_ciphertext
    ciphertext.scan(/.{#{size}}|.+/).join " "
  end

  # transpose raises an IndexError if the length of a subarray doesn't match
  # def ciphertext
  #     normalize_plaintext.chars
  #                        .each_slice(size)
  #                        .entries
  #                        .transpose
  #                        .join
  # end

end
