import sys

#Run file with python3 and the textfile to be encoded as the arguement
#Example:
#python3 huffman_frequency_count.py [filepath]

#Output is a text file with sorted character frequencies in the format 'character-space-frequency'
#Example:
#b 5

text_file = sys.argv[1]
frequency_dict = {}

with open(text_file) as f: 
    txt = f.read()
    for char in txt:
        if char in frequency_dict:
            frequency_dict[char] += 1
        else:
            frequency_dict[char] = 1
sorted_frequency_dict = dict(sorted(frequency_dict.items(), key=lambda item: item[1]))
print(sorted_frequency_dict)

with open('output.txt', 'w') as f:
    for entry in sorted_frequency_dict:
        f.write(entry + ' ' + str(sorted_frequency_dict[entry]) + "\n")



