class String
    def isAnagram(word)
        return self.downcase.chars.sort.join == word.downcase.chars.sort.join
    end
end

# I think that in this case yield() will be more elegant. However, I need to
# study this method before apply it here.
def combine_anagrams(words)
    groups = []
    words.each { |word|
        inserted = false
        groups.each { |item|
            if word.isAnagram(item[0])
                item << word
                inserted = true
            end
        }
        if ! inserted
            list = []
            list << word
            groups << list
        end
    }
    return groups
end
