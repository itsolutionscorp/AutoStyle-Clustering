#HW 1: Ruby calisthenics
#Part 3: anagrams

def combine_anagrams(words)
  output = []
  until words.empty? do
    anagrams = [] # Collect the next set of anagrams
    anagrams << (el = words[0]) # Pickup next item from input and remove it.
    words.delete_at 0
    el_sorted_array = el.downcase.scan(/./).sort # Convert item to a sorted array
    input_new = [] # Collect words not used up while collecting current anagrams
    words.each do |el2|
      el2_sorted_array = el2.downcase.scan(/./).sort # Convert next item to a sorted array
      if el_sorted_array == el2_sorted_array # Array comparison; must be same length and each element match
        anagrams << el2 # Add to current set
      else
        input_new << el2 # This word was not used up; keep it in input array
      end
    end
    output << anagrams # Add new anagram set to output
    words = input_new # Reset input to words not used.
  end
  output
end

input = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
output = combine_anagrams input
puts "#{output}"