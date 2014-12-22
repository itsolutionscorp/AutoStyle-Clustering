def combine_anagrams(words)
  #   <YOUR CODE HERE>
        hWords = Hash.new do |h,k|
                h[k] = []
                #h[k] = Array.new(1)
        end
        #words.each {|w| puts w.downcase.chars.sort.join }
        words.each {|w| hWords[w.downcase.chars.sort.join] << w}
        #aWords = Array.new(hWords.length)
        aWords = []

        @count=0
        #hWords.each {|key| aWords[@count += 1] = hWords[key] }
        hWords.each {|key| aWords << hWords[key] }
        #aWords = [['test','ing'],['a','b']]
        #hWords.each { |h,k| aWords.concat(h[k])}
        #hWords.each{ |h,k| h[k].each{|w| puts w} }

        return aWords
end
