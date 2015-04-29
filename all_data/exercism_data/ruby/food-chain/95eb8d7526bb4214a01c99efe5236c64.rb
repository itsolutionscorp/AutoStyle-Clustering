class FoodChainSong
  attr_reader :animal_collective
  attr_accessor :current_creature,  :song
  attr_writer :stanza

  def initialize
    @animal_collective =%w{ fly spider bird cat dog goat cow horse}
    @song = Hash.new(0)
    self.build_song
  end

  def verse(number)
    puts @song[number]
    @song[number]
  end

  def verses(beginning_verse, selected_verse)
    beginning_verse.upto(selected_verse).map do | key |
    @song[key]
    end.join("\n") + "\n"
  end

  def sing
    verses(1,@song.length)
  end

  def build_song
    animal_collective.each_with_index do |elem, index|
      @song[index + 1] = collect_verses(elem,index)
     end
  end
end

def collect_verses(current_creature, index)
  previous_creature = @animal_collective[index - 1] if index > 0
    common_lines = {
      first_line: "I know an old lady who swallowed a #{current_creature}.\n",
      last_line: "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      wiggled: "that wriggled and jiggled and tickled inside her.\n",
      to_catch: "She swallowed the #{current_creature} to catch the #{previous_creature}.\n"
    }
    creature_specific = {
      spider: "It wriggled and jiggled and tickled inside her.\n",
      bird:     "How absurd to swallow a bird!\n",
      cat:      "Imagine that, to swallow a cat!\n",
      dog:    "What a hog, to swallow a dog!\n",
      goat:  "Just opened her throat and swallowed a goat!\n",
      cow:  "I don't know how she swallowed a cow!\n",
      horse:  "She's dead of course!"
    }

    stanza = case current_creature
      when 'fly'
         common_lines[:first_line] + common_lines[:last_line]
       when 'spider'
         common_lines[:first_line] + creature_specific["#{current_creature}".to_sym] +
         common_lines[:to_catch] + common_lines[:last_line]
       when 'bird'
        to_catch(index,1, common_lines, creature_specific, current_creature)
      when 'cat'
         to_catch(index, 2, common_lines, creature_specific, current_creature)
      when 'dog'
        to_catch(index, 3, common_lines, creature_specific, current_creature)
      when 'goat'
        to_catch(index, 4, common_lines, creature_specific, current_creature)
      when 'cow'
          to_catch(index, 5, common_lines, creature_specific, current_creature)
      when 'horse'
        common_lines[:first_line] + creature_specific[:horse]
    else
     nil
    end
   stanza
  end

  def to_catch(index, cycles, common_lines, creature_specific,c urrent_creature)
    string =''
    cycles.times do
      curr_creature = animal_collective[index]
      pre_creature = animal_collective[index-1]
      unless pre_creature == 'spider'
         string << "She swallowed the #{curr_creature} to catch the #{pre_creature}.\n"
       else
         string << "She swallowed the #{curr_creature} to catch the #{pre_creature} "
      end
       index -= 1
      end

  common_lines[:first_line] + creature_specific["#{current_creature}".to_sym]+
  string + common_lines[:wiggled] + common_lines[:last_line]
  end
