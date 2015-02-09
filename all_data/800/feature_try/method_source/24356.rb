def combine_anagrams(array)
  h = Hash.new
  array.each do |match|
    (h[match.gsub(/\s*/, "").downcase.split("").sort.join] ||= []).push(match)
  end
  puts(h.values.inspect)
end