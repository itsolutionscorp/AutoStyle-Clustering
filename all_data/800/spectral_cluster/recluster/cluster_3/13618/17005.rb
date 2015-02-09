def combine_anagrams(words)
    temp = words.map {|w| w.downcase.split(//).sort.join}.uniq
    list = []
    temp.each do |t|
        sublist = []
        words.each do |w|
            if t == w.downcase.split(//).sort.join
                sublist << w
            end
        end
        list << sublist
    end
    list
end