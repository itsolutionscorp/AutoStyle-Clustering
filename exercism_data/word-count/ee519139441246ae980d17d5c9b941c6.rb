class Phrase < String

  def word_count
    Hash[scan(/\w+/).map{|w|w.downcase}.group_by{|w|w}.map{|w, occurences| [w, occurences.length] }]
  end

end
