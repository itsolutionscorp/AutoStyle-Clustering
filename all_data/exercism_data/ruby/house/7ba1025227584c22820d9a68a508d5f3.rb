require 'ostruct'
require 'pry'

class House
  attr_reader :hero, :structure, :subjects
  def initialize
    @hero = "Jack"
    @structure = "house"
    @subjects = Array.new
    [ ['horse and the hound and the horn', 'belonged to'], ['farmer sowing his corn', 'kept'], ['rooster that crowed in the morn', 'woke'], ['priest all shaven and shorn', 'married'], ['man all tattered and torn', 'kissed'], ['maiden all forlorn', 'milked'], ['cow with the crumpled horn', 'tossed'], ['dog', 'worried'], ['cat', 'killed'], ['rat', 'ate'], ['malt', 'lay'] ].each do |subject, action|
      @subjects << Actor.new({name: subject, action: action})
    end
  end

  def verse(number)
    poem = 'This is '
    poem += subjects_from_the_end_of_the_list(number).map(&:to_s).join(' ')
    poem += ' in ' if number > 1
    poem += "the #{structure} that #{hero} built.\n"
  end

  def verses(start_verse, end_verse)
    poem = ''
    (start_verse..end_verse).each do |verse_number|
      poem += verse(verse_number) + "\n"
    end
    poem
  end

  private

  def subjects_from_the_end_of_the_list(number)
    subjects.reverse.slice(0, number-1).reverse
  end

  class Actor < OpenStruct
    def to_s
      "the #{name} that #{action}"
    end
  end
end
