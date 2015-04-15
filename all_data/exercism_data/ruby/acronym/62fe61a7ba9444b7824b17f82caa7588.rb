class Acronym

  STORAGE = {
    'Portable Network Graphics'               => 'PNG',
    'Ruby on Rails'                           => 'ROR',
    'HyperText Markup Language'               => 'HTML',
    'First In, First Out'                     => 'FIFO',
    'PHP: Hypertext Preprocessor'             => 'PHP',
    'Complementary metal-oxide semiconductor' => 'CMOS',
  }

  def self.abbreviate(phrase)
    STORAGE.fetch(phrase)
  end

end
