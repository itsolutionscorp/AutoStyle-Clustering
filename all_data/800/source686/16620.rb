def combine_anagrams words
  hash = {}

  words.each do |word|
    # As the key, we want to use the lowercase sorted
    # for of the word, as anagrams will always have the
    # same sorted letters. After downcasing, I'm grabbing
    # the array of characters, sorting the array, then joining
    # the array back into a string
    hash[word.downcase.chars.sort.join] ||= []
    hash[word.downcase.chars.sort.join] << word
  end

  # NOTE: This statement:
  #   
  #   this ||= that
  # 
  # is the same as:
  #
  #   this = this || that
  #
  # If "this" is nil, then "this" becomes "that", otherwise it keeps its
  # value. This is used A LOT in Rails to cache stuff, for example the
  # currently logged in user. You might have something like:
  #
  # def current_user
  #   @current_user ||= User.find(some_id)
  # end
  #
  # User.find is an expensive operation, so the first time the method
  # is called it grabs it from the database because @current_user is nil
  # (All instance variables are nil the first time they are accessed)
  # and every time after that, it uses the value already in @current_user.

  # As the result, we just want all the 'values', which are just
  # arrays of anagrams
  return hash.values.to_a
end

arr = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

puts combine_anagrams(arr).inspect


