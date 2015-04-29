class FoodChainSong 

  def initialize
    lyric = File.read(File.expand_path("../README.md",__FILE__)).split("```")[1]
    lyric.slice!("plain\n")
    @verses = lyric.split("\n").chunk{|line| line.empty?}.select{|el| !el.first}
    @verses.map!{|result| result.last.join("\n") + "\n"}
  end
  
  def verse(number)
    @verses[number-1]
  end

  def verses(*numbers)
    numbers.map {|number| verse(number)}.join("\n") + "\n"
  end

  def sing
    verses(1, 8)
  end
end
