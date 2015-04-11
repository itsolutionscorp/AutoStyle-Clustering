class FoodChainSong
  
  CREATURES = ["fly","spider","bird","cat","dog","goat","cow", "horse"]
  LINE_1 = "I know an old lady who swallowed a"
  LINE_2 = {"fly" => "I don't know why she swallowed the fly. Perhaps she'll die.",
            "spider" => "It wriggled and jiggled and tickled inside her.",
            "bird"=> "How absurd to swallow a bird!",
            "cat" => "Imagine that, to swallow a cat!",
            "dog" => "What a hog, to swallow a dog!",
            "goat"=> "Just opened her throat and swallowed a goat!",
            "cow" => "I don't know how she swallowed a cow!", 
            "horse" => "She's dead, of course!"}
  LINE_3_1 = "She swallowed the"    
  LINE_3_2 = "to catch the"

  def verse(n)
    c = CREATURES[n-1]
    the_verse = []
    the_verse << build_line_1(c)
    the_verse << build_line_2(c)
    unless c == "fly" || c == "horse"
      the_verse << build_chained_lines(n) 
      the_verse << build_last_line 
    end
    the_verse.join
  end 
  
  def verses(m,n)
    song =[]
    (m..n).each do |i|
      song << verse(i)
      song << "\n"
    end
    song.join()
  end  
  
  def sing
    verses(1,8)
  end  
  
  private
  def build_line_1(c)
    "#{LINE_1} #{c}.\n"
  end  
  def build_line_2(c)
    "#{LINE_2[c]}\n"
  end 
  def build_chained_lines(n)
    lines = []
    (n-1).downto(1) do |i|
      lines << "#{LINE_3_1} #{CREATURES[i]} #{LINE_3_2} #{CREATURES[i-1]}" 
      lines << " that wriggled and jiggled and tickled inside her" if CREATURES[i-1] == "spider"
      lines << ".\n" 
    end
    lines.join  
  end 
  def build_last_line
    "#{LINE_2["fly"]}\n"
  end     
  
end  
