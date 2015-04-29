def combine_anagrams(words)
    h = {}
    sorted = words.map { |word| word.downcase.chars.sort(&:casecmp).join }
    words.zip(sorted).each do |pair|
       h[pair[1]] ||= []
       h[pair[1]] << pair[0]
    end
    h.values
end