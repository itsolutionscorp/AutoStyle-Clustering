class FoodChainSong

  def verse number
    parser.verse number
  end

  def verses first, last
    (first..last).reduce "" do |result, number|
      result + verse( number )
    end
  end

private

  def parser
    @parser ||= Parser.new
  end

end


class Parser

  VERSES = {
    1 => 9..11,
    2 => 13..16,
    3 => 18..22,
    4 => 24..29,
    5 => 31..37,
    6 => 39..46,
    7 => 48..56,
    8 => 58..59
  }

  def verse number
    poem[VERSES[number]].join
  end

private
  
  def poem
    @poem ||= File.readlines( 'README' )
  end

end
