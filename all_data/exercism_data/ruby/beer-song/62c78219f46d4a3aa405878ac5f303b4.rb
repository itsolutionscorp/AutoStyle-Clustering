class Beer
  def verse(i)
    BeerSongVerse.new(i, next_index(i)).content
  end

  def sing(starting_verse, ending_verse = 0)
    starting_verse.downto(ending_verse).map{|i| verse(i)}.join("\n")+"\n"
  end

  private
  
  def next_index(index)
    index > 0 ? index -1 : 99
  end

end

class BeerSongVerse
  def initialize(index, next_index)
    @index       = index
    @next_index  = next_index
  end

  def content
    first_sentence + second_sentence
  end

  private

  def first_sentence
    start_sentence(bottles_of_beer_on_the_wall(@index)) + ", " + bottles_of_beer(@index) + ".\n"
  end

  def second_sentence
    action + ", " + bottles_of_beer_on_the_wall(@next_index) + ".\n"
  end


  def bottles_of_beer_on_the_wall(count)
    "#{bottles_of_beer(count)} on the wall"
  end

  def bottles_of_beer(count)
    case count
    when 0  
      "no more bottles of beer"
    when 1 
      "1 bottle of beer"
    else        
      "#{count} bottles of beer"
    end
  end

  def action
    case @index
    when 0  
      "Go to the store and buy some more"
    when 1  
      "Take it down and pass it around"
    else       
      "Take one down and pass it around"
    end
  end

  def start_sentence(expression)
    expression.tap {|expression| expression[0] = expression[0].capitalize}
  end

  def on_the_wall
    "on the wall"
  end
end
