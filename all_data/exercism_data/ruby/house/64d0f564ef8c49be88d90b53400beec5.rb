class House

  def initialize 
    @number_of_verses = VERBS_AND_OBJECTS.keys.count
    @song = String.new
  end

  def self.recite
    new.song_builder
  end

  def song_builder
    0.upto(@number_of_verses-1) do |verse|
      this(verse)

      (verse-1).downto(0) do |verse|
        that(verse)
      end

      @song << "\n"
    end

    @song.strip << "\n"
  end

  private
  def this(verse)
    keys = VERBS_AND_OBJECTS.keys
    @song << "This is the #{VERBS_AND_OBJECTS[keys[verse]]}"
    @song << "\n"
  end

  def that(verse)
    keys   = VERBS_AND_OBJECTS.keys
    @song << "that #{keys[verse].to_s.gsub('_', ' ')} the #{VERBS_AND_OBJECTS[keys[verse]]}"
    @song << "\n"
  end

  VERBS_AND_OBJECTS = {
    lay_in: "house that Jack built.",
    ate: "malt",
    killed: "rat",
    worried: "cat",
    tossed: "dog",
    milked: "cow with the crumpled horn",
    kissed: "maiden all forlorn",
    married: "man all tattered and torn",
    woke: "priest all shaven and shorn",
    kept: "rooster that crowed in the morn",
    belonged_to: "farmer sowing his corn",
    null: "horse and the hound and the horn"
  }

end
