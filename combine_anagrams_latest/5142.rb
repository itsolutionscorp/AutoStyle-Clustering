def combine_anagrams(words)
    map = {}
    arr = []
    words.each do |x|
    map.has_key?(x.downcase.split('').sort.join) ? map.store(x.downcase.split('').sort.join,map.fetch(x.downcase.split('').sort.join).push(x)): map.store(x.downcase.split('').sort.join,[x])
    end
    map.each_key{|key| arr.push(map.fetch(key))}
    return arr
end