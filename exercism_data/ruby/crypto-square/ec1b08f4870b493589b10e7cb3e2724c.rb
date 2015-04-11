class Crypto

  def initialize(str)
    @text = str
  end

  def normalize_plaintext
    @text.gsub(/\W/,"").downcase
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    size.times.with_object("") do |column, str|
      plaintext_segments.each {|segment| str << segment[column] if segment[column]}
    end
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(" ")
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end
end
