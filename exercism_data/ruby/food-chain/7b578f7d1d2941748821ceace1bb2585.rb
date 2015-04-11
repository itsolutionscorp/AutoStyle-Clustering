class FoodChainSong
  attr_reader :animal_collective, :song, :common_lines, :creature_specific
  attr_accessor :current_creature, :previous_creature

  def initialize
    @animal_collective = %w{ fly spider bird cat dog goat cow horse}
    @song = Hash.new(0)
    self.build_song
  end

  def verse(number)
    @song[number]
  end

 def verses(beginning_verse, selected_verse)
    beginning_verse.upto(selected_verse).map do | key |
    @song[key]
    end.join("\n") + "\n"
  end

  def build_song
    collect_verses
    animal_collective.each_with_index do |critter, index|
      song[index+1] = arrangement(critter, index)
    end
    song
  end

  def collect_verses
    @common_lines = {
      first_line:  Proc.new { "I know an old lady who swallowed a #{current_creature}.\n" },
      last_line:  "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      wriggled: "that wriggled and jiggled and tickled inside her.\n",
      catching:  Proc.new { "She swallowed the #{current_creature} to catch the #{previous_creature}.\n" }
    }
    @creature_specific = {
      spider:    "It wriggled and jiggled and tickled inside her.\n",
      bird:       "How absurd to swallow a bird!\n",
      cat:         "Imagine that, to swallow a cat!\n",
      dog:       "What a hog, to swallow a dog!\n",
      goat:      "Just opened her throat and swallowed a goat!\n",
      cow:       "I don't know how she swallowed a cow!\n",
      horse:    "She's dead of course!"
    }
  end

  def arrangement(critter, index)
    self.current_creature = critter
    self.previous_creature = animal_collective[index-1] if index > 0
    stanza = case current_creature
      when 'fly'
        common_lines[:first_line].call + common_lines[:last_line]
      when 'spider'
        common_lines[:first_line].call + creature_specific["#{current_creature}".to_sym] +
        common_lines[:catching].call + common_lines[:last_line]
      when 'bird'
        compose_repetative_verses(1, index)
      when 'cat'
        compose_repetative_verses(2, index)
      when 'dog'
        compose_repetative_verses(3, index)
      when 'goat'
        compose_repetative_verses(4,index)
      when 'cow'
        compose_repetative_verses(5,index)
      when 'horse'
        common_lines[:first_line].call + creature_specific["#{current_creature}".to_sym]
      else
        nil
      end
    stanza
  end

  def compose_repetative_verses(cycles, index)
    composition = ""
    cycles.times do
      current_creature = animal_collective[index]
      previous_creature = animal_collective[index-1]

      unless previous_creature == 'spider'
        composition << "She swallowed the #{current_creature} to catch the #{previous_creature}.\n"
      else
        composition << "She swallowed the #{current_creature} to catch the #{previous_creature} "
      end
      index -=1
    end

    common_lines[:first_line].call + creature_specific["#{current_creature}".to_sym] + composition +
    common_lines[:wriggled] + common_lines[:last_line]
  end
end
