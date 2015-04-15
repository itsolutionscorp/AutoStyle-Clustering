class FoodChainSong
  def verse(n)
    file_opener
    #looks for correct verse by parsing empty lines
    #1 paragraph == 1 line('\n')
    f = 1
    while f < n
      f += 1 if @file.gets.strip.empty?
    end
    #copy each line in result until empty string or end of song
    #using 'loop' instead of 'begin' because i read on stackoverflow 
    #than Matz advise it.
    result = String.new
    loop do
      temp = @file.gets
      break if temp.strip.empty? or temp.match(/```/)
      result << temp
    end
    @file.close
    result
  end

  def verses(start, end_)
    (start..end_).each_with_object(String.new) do |song_verse, result|
      result << verse(song_verse)
      result << "\n"
    end
  end

  def sing
    verses(1,8)
  end

  def file_opener
    raise IOError.new("file doesn't exist README.md") unless File.exist?("README.md")
    @file = File.new("README.md", "r")
    #position buffer at the start of the song, "````plain"
    while not @file.gets.match(/plain\n/); end
  end
end
