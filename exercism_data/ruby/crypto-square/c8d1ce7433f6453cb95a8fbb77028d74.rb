class Crypto

  attr_reader :text, :plaintexted

  def initialize text
    @text = text
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice( final_size ).to_a.map( &:join ).join' '
  end

  def ciphertext 
    plaintext_segments.map( &:chars ).first.zip( *plaintext_segments[1..-1]
      .map( &:chars ) ).compact.join
  end

  def plaintext_segments
    @plaintexted ||= normalize_plaintext.chars.each_slice( size ).map &:join
    plaintexted  
  end

  def normalize_plaintext
    text.gsub( /\W/, '').downcase
  end

  def size
    ( 1..normalize_plaintext.length ).find do |number| 
      number ** 2 >= normalize_plaintext.length
    end
  end

private

  def final_size
    plaintexted.size
  end

end
