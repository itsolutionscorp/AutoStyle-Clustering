class String
    def sort
        self.chars.sort.join
    end
end

def combine_anagrams(anagrams)
    result = anagrams.inject([]) do |memo, a|
        memo.push anagrams.select{|b| a.downcase.sort == b.downcase.sort }.sort
    end.uniq
end