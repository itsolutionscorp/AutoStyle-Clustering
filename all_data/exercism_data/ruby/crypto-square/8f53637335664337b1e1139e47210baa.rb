class Crypto
  attr_accessor :msg
  def initialize(msg)
    @msg = msg
  end

  def normalize_plaintext
    @msg = extract(@msg)
  end

  def size
    str = normalize_plaintext
    if Math.sqrt(str.length) == Math.sqrt(str.length).to_i
      return Math.sqrt(str.length)
    else
      Math.sqrt(str.length).to_i + 1
    end
  end

  def plaintext_segments
    split_string
  end

  def ciphertext
    words = plaintext_segments
    cipher = ""

    (0..words.length).each do |ctr|
      words.each do |word|
        cipher << word[ctr] if word[ctr]
      end
      ctr +=1
    end
    cipher
  end

  def normalize_ciphertext
    str = ciphertext
    length = size
    results = ""
    ctr = 1
    str.each_char do |letter|
      results << letter
      results << " " if ctr % length == 0
      ctr += 1
    end
    results.rstrip!
    results
  end

  private

  def split_string
    str = normalize_plaintext.split(//)
    length = size
    results = []
    ctr = 0
    while ctr < str.length
      results << str.slice(ctr...ctr+length).join
      ctr += length
    end
    results
  end

  def extract(string)
    str = string.gsub(/\W/, "").downcase
    str
  end

end
