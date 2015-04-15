class Acronym

  def self.abbreviate(str)
    result = ""

    words = str.split(/\W/)
    words.reject!(&:empty?)
    words.each {|word| result << word[0]}
    result.upcase!

    alt = str.scan(/[[:upper:]]/).join

    # I hate PHP: HP
    alt.chop!.chop! if alt.size == 5

    result.size < alt.size ? alt : result
  end

end
