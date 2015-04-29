class FoodChainSong
  def initialize
    @song = import_song
  end

  def verse(num)
    @song_structure["verse#{num}"]
  end

  def verses(index1, index2)
    @song_structure["verse#{index1}"] + @song_structure["verse#{index2}"]
  end

  def sing
    @song.first
  end


  def import_song
    #get range of song in readme
    range = []
    IO.readlines('README.md').each_with_index do |file, index|
      range << index if file.include?('```')
    end
    #store range
    @song = IO.readlines('README.md')[range.first+1, range.last-range.first-1]
    build_verses
  end

  def build_verses
    #create song structure
    ctr = 1
    @song_structure = Hash.new { |h, k| h[k] = "" }
    @song.each do |line|
      if line == "\n"
        ctr += 1
        next
      else
        @song_structure["verse#{ctr}"] << line
      end
    end
  end

end
