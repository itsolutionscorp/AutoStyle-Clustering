require "pry"
class Acronym

  def self.abbreviate(str)
    words = str.split(/\W+/)
    result = words.map { |word| word[0] }.join.upcase

    alt = str.scan(/[[:upper:]]/).join

    # I hate PHP: HP
    alt.chop!.chop! if alt.size == 5

    result.size < alt.size ? alt : result
  end

end
