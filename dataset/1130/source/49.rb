def combine_anagrams words
  unless words.nil?
    words.group_by {|word| word.downcase.split(//).sort}.values
  end
end