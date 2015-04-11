class Atbash

  attr_reader :text

  ALPHABET = %w[ a b c d e f g h i j k l m n o p q r s t u v w x y z ]

  def self.encode text
    Atbash.new( text ).send:text_splited 
  end

  def initialize text
    @text = text
  end   

private

  def text_splited 
    text_maped_to_code.each_slice( 5 ).map( &:join ).join ' '
  end
  
  def text_maped_to_code 
    array_of_of_letter_without_punctuatuion.map do |letter|
      map letter.downcase
    end
  end

  def array_of_of_letter_without_punctuatuion
    text.gsub( /\W/, '' ).chars
  end

  def map letter 
    if ALPHABET.include? letter
      ALPHABET.reverse[  ALPHABET.index( letter ) ]
    else
      letter
    end
  end

end
