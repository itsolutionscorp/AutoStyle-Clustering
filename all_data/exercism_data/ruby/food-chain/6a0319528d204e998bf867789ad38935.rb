class FoodChainSong
  VERSES =
    { 
      1 => { 
        :animal => 'fly', 
        :extra => ''},
      2 => { 
        :animal => 'spider', 
        :extra => 'It wriggled and jiggled and tickled inside her.'},
      3 => { 
        :animal => 'bird', 
        :extra => 'How absurd to swallow a bird!'},
      4 => { 
        :animal => 'cat', 
        :extra => 'Imagine that, to swallow a cat!'},
      5 => { 
        :animal => 'dog', 
        :extra => 'What a hog, to swallow a dog!'},
      6 => { 
        :animal => 'goat', 
        :extra => 'Just opened her throat and swallowed a goat!'},
      7 => { 
        :animal => 'cow',  
        :extra => 'I don\'t know how she swallowed a cow!'},
      8 => { 
        :animal => 'horse', 
        :extra => 'She\'s dead, of course!'},
    }
  def verse(verse)
    raise ArgumentError 'there are only 8 verses' if verse > 8
    raise ArgumentError 'verse needs to be a positive number' if verse < 1
    lyrics = "I know an old lady who swallowed a #{VERSES[verse][:animal]}.\n"
    if verse == 8 then
      lyrics += "#{VERSES[verse][:extra]}\n"
      return lyrics
    elsif verse > 1 then
      lyrics += "#{VERSES[verse][:extra]}\n"
      verse.downto(2).each do |index|
        lyrics += "She swallowed the #{VERSES[index][:animal]}"
        lyrics += " to catch the #{VERSES[index-1][:animal]}"
        if index == 3
          lyrics += ' that wriggled and jiggled and tickled inside her'
        end
        lyrics += ".\n"
      end
    end
    lyrics += "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    lyrics
  end
  def verses(first, last)
    raise ArgumentError 'first and last need to be a valid range' if first >= last
    raise ArgumentError 'first and last need to be a valid range' if first > 8 or last > 8
    raise ArgumentError 'first and last need to be a valid range' if first < 1 or last < 1
    (first..last).inject('') { |song, n| song += "#{verse(n)}\n" }
  end
  def sing
    verses(1,8)
  end
end
