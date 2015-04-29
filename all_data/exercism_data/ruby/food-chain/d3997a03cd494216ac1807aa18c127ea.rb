class FoodChainSong
  
  def initialize
    @song = ""
  end
  
  def verse (num)
    song_over = false
    while song_over == false
      case num
      when 8 
          @song << "I know an old lady who swallowed a horse.\n" +
                    "She's dead, of course!\n"
            song_over = true
      when 7 
          start_with ||= "I know an old lady who swallowed a cow.\n" +
                         "I don't know how she swallowed a cow!\n" 
          continue_with = "She swallowed the cow to catch the goat.\n" 
          @song << start_with + continue_with
          start_with.clear
            num = 6
      when 6 
          start_with ||= "I know an old lady who swallowed a goat.\n" +
                         "Just opened her throat and swallowed a goat!\n"
          continue_with = "She swallowed the goat to catch the dog.\n"
          @song << start_with + continue_with
          start_with.clear
            num = 5
      when 5 
          start_with ||= "I know an old lady who swallowed a dog.\n" +
                         "What a hog, to swallow a dog!\n"
          continue_with = "She swallowed the dog to catch the cat.\n" 
          @song << start_with + continue_with
          start_with.clear
            num = 4
      when 4 
          start_with ||= "I know an old lady who swallowed a cat.\n" + 
                         "Imagine that, to swallow a cat!\n"
          continue_with = "She swallowed the cat to catch the bird.\n"
          @song << start_with + continue_with
          start_with.clear
            num = 3
      when 3  
          start_with ||= "I know an old lady who swallowed a bird.\n" +
                         "How absurd to swallow a bird!\n"
          continue_with = "She swallowed the bird to catch the spider that "
          @song << start_with + continue_with
          start_with.clear
            num = 2
      when 2 
          start_with ||= "I know an old lady who swallowed a spider.\nIt "
          continue_with = "wriggled and jiggled and tickled inside her.\n" +
                          "She swallowed the spider to catch the fly.\n" 
          @song << start_with + continue_with
          start_with.clear
            num = 1
      when 1 
          start_with ||=  "I know an old lady who swallowed a fly.\n"
          continue_with = "I don't know why she swallowed the fly. Perhaps she'll die.\n"
          @song << start_with + continue_with
          start_with.clear
        
          song_over = true
          start_with = nil
      end
    end
      return @song
  end
  
  def verses (first, last)
    @whole_song = ""
    first.upto(last){|i| @song.clear
      @whole_song << verse(i) + "\n" 
    }
    return @whole_song 
  end
  
  def sing
    verses(1,8)
  end
  
end
