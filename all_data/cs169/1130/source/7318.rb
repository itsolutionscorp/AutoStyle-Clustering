# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

def combine_anagrams(words)
  h=Hash.new;
  counter=0;
  arr=Array.new;
  arrcounter=0;
  stringarray=Array.new;
  0.upto(words.size-1){
    temp=words[counter];
    temp=temp.downcase;
    a=temp.split('').sort.join;
    a=a.downcase;
    /puts(a);/
    if h.has_key?(a)
      tempo=Array.new;
      tempo=arr[h[a]];
      arr[h[a]]<<words[counter];
      /puts(words[counter])
      puts(h[a])/
    else 
      arra=Array.new;
      arra=arra<<words[counter];
      arr[arrcounter]=arra;
      q=words[counter].downcase;
      q=q.split('').sort.join;
      q=q.downcase;
      h[q.downcase]=arrcounter;
      arrcounter=arrcounter+1;
    end

    counter=counter+1;
  }
  return arr;
end
temp=['Cars', 'for', 'potatoes', 'raCs', 'four','scar', 'creams', 'scream'] ;
puts(combine_anagrams(temp))
/a=combine_anagrams(temp);
puts[a[0]];/
/p="yoo";
puts(p.split('').sort.join)/