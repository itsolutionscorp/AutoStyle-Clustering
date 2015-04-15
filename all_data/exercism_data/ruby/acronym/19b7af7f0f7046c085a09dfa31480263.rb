class Acronym
  def self.abbreviate(words)
    acronym = ''
    return words.split(':').first if words.include?(':')

    words.split('-').join(' ').split(' ').map{ |w| w[0].upcase}.join
  end
end
