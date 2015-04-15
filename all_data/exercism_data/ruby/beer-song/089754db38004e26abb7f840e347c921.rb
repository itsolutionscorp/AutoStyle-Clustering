# beer_song.rb
class BeerSong
  TOTAL_VERSES = 99

  attr_reader :number

  def verse(number)
    @number = number
    build_verse
  end

  def verses(from, to)
    from.downto(to).map do |number|
      verse(number) + "\n"
    end.join
  end

  def sing
    verses(TOTAL_VERSES, 0)
  end

  private

  def build_verse
    before = compile(:before).capitalize
    action = compile(:action)
    decrement_number
    after = compile(:after)
    [before, action, after].join
  end

  def decrement_number
    @number -= 1
    @number = TOTAL_VERSES if @number == -1
  end

  def default_fragments
    FRAGMENTS[:default].merge(count_hash)
  end

  def number_specific_fragments
    FRAGMENTS[:"when_#{number}"] || {}
  end

  def count_hash
    { number: number }
  end

  def merged_fragments
    default_fragments.merge(number_specific_fragments)
  end

  def compile(key)
    fragment = merged_fragments[key]
    return fragment.to_s unless fragment.is_a?(String)
    fragment.gsub(/:[a-z_]+/) do |fragment_key|
      compile(fragment_key[1..-1].to_sym)
    end
  end

  FRAGMENTS = {
    default: {
      before: ":bottles_on_wall, :bottles_of_beer.\n",
      action: 'Take :take_what down and pass it around',
      after: ", :bottles_on_wall.\n",
      bottles_of_beer: ':bottle_count :bottles of beer',
      bottles_on_wall: ':bottles_of_beer on the wall',
      bottle_count: ':number',
      bottles: 'bottles',
      take_what: 'one'
    },
    when_1: {
      bottles: 'bottle',
      take_what: 'it'
    },
    when_0: {
      action: 'Go to the store and buy some more',
      bottle_count: 'no more'
    }
  }
end
