class Acronym
  def self.abbreviate(phrase)
    dictionary[phrase.downcase]
  end

  class << self
    private

    def dictionary
      @dictionary ||= {
        'portable network graphics' => 'PNG',
        'ruby on rails' => 'ROR',
        'hypertext markup language' => 'HTML',
        'first in, first out' => 'FIFO',
        'php: hypertext preprocessor' => 'PHP',
        'complementary metal-oxide semiconductor' => 'CMOS'
      }
    end
  end
end
